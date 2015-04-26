//
//  SaveNoteView.m
//  CarCola
//
//  Created by Hao Zhou on 3/5/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import "SaveNoteView.h"
#import "ThirdViewController.h"

@interface SaveNoteView ()

@end

@implementation SaveNoteView

- (void)viewDidLoad {
    [super viewDidLoad];
    
    [self configureView];
}

- (void)configureView {
//    self.clickCounter = 0;
    
    self.myCars = [[NSMutableArray alloc] init];
    
    self.userNotes.hidden = YES;
    
    NSLog(@"the color is %@ and %@", _carColor, _userNotes.hidden?@"YES":@"NO");
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


- (IBAction)BackToHome:(id)sender {
    
    [self dismissViewControllerAnimated:YES completion:nil];
    
    [self configureView];

    
    NSLog(@"Back to Home!");
}

- (IBAction)moreNotes:(id)sender {
    
    if (self.carMake.text.length > 0 & self.userNotes.hidden == YES){
        self.userNotes.hidden = NO;
    }else{
        self.userNotes.hidden = YES;
    }
    
    NSLog(@"The userNotes is hidden? %@", self.userNotes.hidden?@"YES":@"NO");

}

- (IBAction)rememberMyCar:(id)sender {
    if (_carMake.text.length > 0 && _carModel.text.length > 0 && _carTrim.text.length > 0 && _carYear.text.length > 0 && _carColor.text.length > 0)
    {
        //temp variable for test
//        NSString *imgLink = @"garage-4";
        
        MyCar *addedCar = [[MyCar alloc] initWithMake:self.carMake.text model:self.carModel.text trim:self.carTrim.text year:self.carYear.text color:self.carColor.text notes:self.userNotes.text];
        
        [self.myCars addObject:addedCar];
        
        [self cleanTextBox];
        
//        NSLog(@"the new added car is %@", addedCar.carPhotoLink);

    
    } else {
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Something Missed" message:@"Please check if you did miss something to save for future recall?" delegate:self cancelButtonTitle:@"I Got It" otherButtonTitles:nil];
//        [alert addButtonWithTitle:@"Yes"];
        [alert show];
    }
    
    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
    NSData *data = [NSKeyedArchiver archivedDataWithRootObject:self.myCars];
    [userDefaults setObject:data forKey:@"savedCars"];
    [userDefaults synchronize];
    

}

-(void)cleanTextBox {
    self.carMake.text = nil;
    self.carModel.text = nil;
    self.carTrim.text = nil;
    self.carYear.text = nil;
    self.carColor.text = nil;
    self.userNotes.text = nil;
    
    self.userNotes.hidden = YES;
    
}

- (IBAction)goToMyGarage:(id)sender {
    NSUserDefaults *sound = [NSUserDefaults standardUserDefaults];
    NSString *playAudio = [sound objectForKey:@"audio"];
    if ([playAudio integerValue]) {
        [self playSound:@"Door"];
    }
    
}

- (IBAction)CleanHomeButton:(id)sender {
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Alert!" message:@"Please confirm you are willing to erase all saved information." delegate:self cancelButtonTitle:@"Cancel" otherButtonTitles:@"Yes", nil];
    //    [alert addButtonWithTitle:@"Yes"];
    [alert show];
    NSUserDefaults *sound = [NSUserDefaults standardUserDefaults];
    NSString *playAudio = [sound objectForKey:@"audio"];
    if ([playAudio integerValue]) {
        [self playSound:@"CleanShort"];
    }

}

- (void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex {
    if (buttonIndex == [alertView firstOtherButtonIndex]) {
        
        NSString *appDomain = [[NSBundle mainBundle] bundleIdentifier];
        [[NSUserDefaults standardUserDefaults] removePersistentDomainForName:appDomain];
        self.myCars = [[NSMutableArray alloc] init];
        
        NSLog(@"Users data was erased successfully!");
    }
}

- (void)playSound:(NSString*)path{
    
    self.soundPath = [[NSBundle mainBundle]pathForResource:path ofType:@"wav"];
    self.audio = [[AVAudioPlayer alloc] initWithContentsOfURL:[NSURL fileURLWithPath:self.soundPath] error:NULL];
    self.audio.meteringEnabled = YES;
    self.audio.delegate = self;
    [self.audio prepareToPlay];
    [self.audio play];
}

@end
